package tree;

import java.util.Objects;

/*
Lớp Person chứa thông tin và hành vi cần thiết cho mỗi hồ hơ nhân viên
 */
class Person implements Comparable<Person> {

    private String id, name, birthplace;
    private String dob;

    // Constructor method
    Person() {

    }

    Person(String id, String name, String birthplace, String dob) {
        this.id = id;
        this.name = name;
        this.birthplace = birthplace;
        this.dob = dob;
    }

    String getId() {

        return id;

    }

    void setId(String id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getBirthplace() {
        return birthplace;
    }

    void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    String getDob() {
        return dob;
    }

    //2. getter, setter
    void setDob(String dob) {
        this.dob = dob;
    }

    //  toString  method for display
    @Override
    public String toString() {
        return String.format("%-5s %-20s %-15s %-15s", id, name, dob, birthplace);
    }

    // equals method (compare by ID) for insert to tree
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Person p = (Person) obj;
        return this.getId().equalsIgnoreCase(p.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.birthplace);
        hash = 17 * hash + Objects.hashCode(this.dob);
        return hash;
    }

    //CompareTo
    @Override
    public int compareTo(Person o) {
        return this.getId().compareToIgnoreCase(o.getId());
    }

}
