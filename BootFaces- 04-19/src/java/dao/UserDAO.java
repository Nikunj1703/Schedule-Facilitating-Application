/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import bean.*;
import java.util.ArrayList;
/**
 *
 * @author Gunjan
 */
public interface UserDAO {
    
      public int authenticate(User user);
      public int checkUlidPresent(String ulid);
      public boolean isFacultyUlid(String ulid);
      public int createAccount(User user);
      
      public boolean checkToDoItemExists(ProfessorBean professorBean);
      public ToDoBean fetchToDoItems(ProfessorBean professorBean);
      
      public boolean checkCommentsExists(ProfessorBean professorBean, ToDoBean toDoBean);
      public ReviewCommentBean fetchComments(ProfessorBean professorBean, ToDoBean toDoBean);
      public boolean updateComments(ReviewCommentBean reviewCommentBean,ToDoBean toDoBean); 
      public boolean addComments (ReviewCommentBean reviewCommentBean,ToDoBean toDoBean);
 
       
      public boolean updateToDoStatusDB(ToDoBean toDoBean, ProfessorBean professorBean);
     
}
