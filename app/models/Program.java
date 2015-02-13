package models;

import javax.persistence.*;
import java.util.*;

import play.db.ebean.*;

@Entity(name= "PROGRAM")
public class Program extends Model {

	/**
     * By default column names of the entities are the same as variable names
     */
    @Id
    private Long id;
    private String name;
    private String type;	
    
    @OneToOne(cascade=CascadeType.REMOVE)
    public User owner;
    
    @ManyToMany(cascade=CascadeType.REMOVE)
    public List<User> members = new ArrayList<User>();
	
    public Program(String name, User owner, User members) {
        this.name = name;
        this.owner = owner;
	this.members.add(owner);
    }
	
    public static Model.Finder<Long, Program> find = new Model.Finder(Long.class, Program.class);
	
    public static Program create(String name, String owner) {
        Program program = new Program(name, User.find.ref(owner), User.find.ref(owner));
	program.save();
        program.saveManyToManyAssociations("members");
	return program;
    }
	
    public static List<Program> findInvolving(String user) {
        return find.where().eq("members.email", user).findList();
    }
}
