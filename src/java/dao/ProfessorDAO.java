/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ProfessorBean;
import java.util.ArrayList;

/**
 *
 * @author Nikunj
 */
public interface ProfessorDAO {
    public ArrayList findAllProfessors();
    
    public int updateProfessorInfo(ProfessorBean updatedProfessor);
    
    public int addNewProfessor(ProfessorBean newProfessor);
    
    public ProfessorBean getProfessor(String ulid);
    
    public ArrayList findAvailableProfessors();
}
