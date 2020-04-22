package be.pxl.student.entity;

import javax.persistence.*;

@Entity
@NamedQueries(
        { @NamedQuery(name = "findLabelByName", query = "SELECT l FROM Label l WHERE l.name = :name"), @NamedQuery(name = "findAllLabels", query = "")}
)
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Label() {
        // JPA only
    }

    public Label(String name) { setName(name); }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
