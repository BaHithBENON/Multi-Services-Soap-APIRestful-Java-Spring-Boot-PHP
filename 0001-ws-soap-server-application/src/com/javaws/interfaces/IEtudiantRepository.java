package com.javaws.interfaces;

import com.javaws.entities.Etudiant;
import com.javaws.exceptions.DAOException;

public interface IEtudiantRepository extends IDao<Etudiant> {
	/**
	 * @param nom
	 * @param prenom
	 * @return
	 * @throws RepositoryException
	 */
	Etudiant readByNomPrenom(String nom, String prenom) throws DAOException;
}
