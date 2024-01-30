package com.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dao.IDaoUser;
import com.api.dao.UserDaoImpl;
import com.api.entities.Response;
import com.api.entities.User;

@RestController
@RequestMapping("/api")
public class UserController {
	private IDaoUser dao;
	private Response<User> response;

	public UserController() {
		dao = new UserDaoImpl();
		response = new Response<>();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Response<User>> getUser(@PathVariable(value = "id") int id) {		
		try {
			User user = dao.read(id);
			if (user != null) {
				prepareSuccessResponse("L'utilisateur avec l'id " + id + " a été trouvé !");
				response.setEntity(user);
				
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			prepareFailureResponse(e);	
			return ResponseEntity.internalServerError().body(response);
		}

		prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'utilisateur avec l'id " + id + " n'a pas été trouvé !");		
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/users")
	public ResponseEntity<Response<User>> getUsers() {
		try {
			List<User> users = dao.list();
			if (users.isEmpty()) {
				prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "La liste des utilisateurs est vide !");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			prepareSuccessResponse(users.size() + " user(s) trouvé(s) !");
			response.setEntities(users);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<Response<User>> setUser(@RequestBody User user) {
		try {
			dao.create(user);
			prepareSuccessResponse("L'utilisateur a été ajouté avec succès !");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response<User>> login(@RequestBody User user) {
		try {
			User logged = dao.login(user.getLogin(), user.getPassword());
			prepareSuccessResponse("L'utilisateur a été retrouvé !");
			response.setEntity(logged);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PutMapping("/user/{id}")
    public ResponseEntity<Response<User>> updateUser(@PathVariable Integer id, @RequestBody User user) {
		try {
			User currentUser = dao.read(id);
	        if (currentUser != null) {
	        	currentUser.setLogin(user.getLogin());
	        	currentUser.setPassword(user.getPassword());

				dao.update(currentUser);
		        prepareSuccessResponse("L'utilisateur a été modifié avec succès !");
		        return ResponseEntity.ok(response);
	        } else {
	        	prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'utilisateur avec l'id " + id + " n'a pas été trouvé !");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
    }
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Response<User>> deleteClient(@PathVariable Integer id) {
		try {
			if (null != dao.read(id)) {	
				dao.delete(id);
		        prepareSuccessResponse("L'utilisateur a été supprimé avec succès !");
		        return ResponseEntity.ok(response);
	        } else {
	        	prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'utilisateur avec l'id " + id + " n'a pas été trouvé !");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	private void prepareSuccessResponse (String message) {
		prepareResponse(HttpStatus.OK.value(), Status.OK.name(), message);		
	}

	private void prepareFailureResponse (Exception e) {
		prepareResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), Status.KO.name(), e.getMessage());
	}
	
	private void prepareFailureResponse (int code, String message) {
		prepareResponse(code, Status.KO.name(), message);
	}

	private void prepareResponse (Integer code, String status, String message) {
		response.setCode(code);
		response.setStatus(status);
		response.setMessage(message);

		response.setEntity(null);
		response.setEntities(null);
	}
}
