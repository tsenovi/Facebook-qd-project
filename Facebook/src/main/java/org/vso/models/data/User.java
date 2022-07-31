package org.vso.models.data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false)
    private int age;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    private List<Post> posts;

    @OneToMany(targetEntity = Friendship.class,
            mappedBy = "receiver",
            fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Integer.class)
    private List<Friendship> friendShips;

    @OneToMany(targetEntity = Friendship.class,
            mappedBy = "receiver",
            fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Integer.class)
    private List<User> friends;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Image> images;


    protected User() {
    }

    public User(String email, String password, String firstName, String lastName, int age) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPosts(Post post) {
        this.posts.add(post);
    }

    public List<Friendship> getFriendShips() {
        return friendShips;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend){this.getFriends().add(friend);}

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void removeImage(Image image) {
        this.images.remove(image);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, firstName='%s', lastName='%s', email='%s']", id, firstName, lastName, email);
    }
}
