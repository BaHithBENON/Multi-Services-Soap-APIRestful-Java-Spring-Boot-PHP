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

import com.api.dao.EtudiantDaoImpl;
import com.api.dao.IDao;
import com.api.entities.Etudiant;
import com.api.entities.Response;

@RestController
@RequestMapping("/api")
public class EtudiantController {
	private IDao<Etudiant> dao;
	private Response<Etudiant> response;

	public EtudiantController() {
		dao = new EtudiantDaoImpl();
		response = new Response<>();
	}

	@GetMapping("/etudiant/{id}")
	public ResponseEntity<Response<Etudiant>> getEtudiant(@PathVariable(value = "id") int id) {		
		try {
			Etudiant etudiant = dao.read(id);
			if (etudiant != null) {
				prepareSuccessResponse("L'étudiant avec l'id " + id + " a été trouvé !");
				response.setEntity(etudiant);
				
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			prepareFailureResponse(e);	
			return ResponseEntity.internalServerError().body(response);
		}

		prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'étudiant avec l'id " + id + " n'a pas été trouvé !");		
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/etudiants")
	public ResponseEntity<Response<Etudiant>> getEtudiants() {
		try {
			List<Etudiant> etudiants = dao.list();
			if (etudiants.isEmpty()) {
				prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "La liste des étudiants est vide !");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			prepareSuccessResponse(etudiants.size() + " étudiant(s) trouvé(s) !");
			response.setEntities(etudiants);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PostMapping("/etudiant")
	public ResponseEntity<Response<Etudiant>> setEtudiant(@RequestBody Etudiant etudiant) {
		try {
			dao.create(etudiant);
			prepareSuccessResponse("L'étudiant a été ajouté avec succès !");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PutMapping("/etudiant/{id}")
    public ResponseEntity<Response<Etudiant>> updateEtudiant(@PathVariable Integer id, @RequestBody Etudiant etudiant) {
		try {
			Etudiant currentEtudiant = dao.read(id);
	        if (currentEtudiant != null) {
				currentEtudiant.setNom(etudiant.getNom());
				currentEtudiant.setPrenom(etudiant.getPrenom());
				currentEtudiant.setMoyenne(etudiant.getMoyenne());

				dao.update(currentEtudiant);
		        prepareSuccessResponse("L'étudiant a été modifié avec succès !");
		        return ResponseEntity.ok(response);
	        } else {
	        	prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'étudiant avec l'id " + id + " n'a pas été trouvé !");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
		} catch (Exception e) {
			prepareFailureResponse(e);
			return ResponseEntity.internalServerError().body(response);
		}
    }
	
	@DeleteMapping("/etudiant/{id}")
	public ResponseEntity<Response<Etudiant>> deleteClient(@PathVariable Integer id) {
		try {
			if (null != dao.read(id)) {	
				dao.delete(id);
		        prepareSuccessResponse("L'étudiant a été supprimé avec succès !");
		        return ResponseEntity.ok(response);
	        } else {
	        	prepareFailureResponse(HttpStatus.NOT_FOUND.value(), "L'étudiant avec l'id " + id + " n'a pas été trouvé !");
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

enum Status {
	KO, OK
}
