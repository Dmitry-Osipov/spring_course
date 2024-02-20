package hibrenate_many_to_many.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "section")
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(  // То же, что и JoinColumn, но для связи many to many (ибо теперь связь производится через
            // промежуточную таблицу, а не столбец).
            name = "child_section",
            joinColumns = @JoinColumn(name = "section_id"),  // С помощью JoinColumn прописываем, с помощью какого
            // столбца таблица child_section связана с нашей таблицей этого класса (section).
            inverseJoinColumns = @JoinColumn(name = "child_id")  // Указываем, с помощью какого столбца таблица
            // child_section будет связана с таблицей другого класса (children).
    )
    private List<Child> children;

    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    public void addChildToSection(Child child) {
        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(child);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
