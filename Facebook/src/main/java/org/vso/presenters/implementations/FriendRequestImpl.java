package org.vso.presenters.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.vso.constants.FriendStatus;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.FriendRequest;
import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.views.View;

public class FriendRequestImpl{
    private final View view;
    private final UserDao<User> allUsers;
    private final AuthenticationService authenticationService;

    public FriendRequestImpl() {
        this.view = new View();
        this.allUsers = new UserDaoImpl();
        this.authenticationService = new AuthenticationServiceImpl();
    }


    public User search(){
        view.show("Name:");
        String name = view.getUserTextInput();
        for (int i = 0; i < allUsers.getAll().size(); i++) {
            if (allUsers.getAll().get(i).getFirstName().equals(name)){
                view.showNumber(i);
                view.showTextWithoutNextLine(". " + allUsers.getAll().get(i).getFirstName() +
                        " " + allUsers.getAll().get(i).getLastName() + "\n");

            }
        }
        int searched = view.getUserDecimalInput();
        return allUsers.getAll().get(searched);
    }

    public void sendFriendRequest(){
        User searchedUser = search();
        FriendRequest friendRequest = new FriendRequest(authenticationService.getLoggedUser().getId(),
                searchedUser.getId(), FriendStatus.SENT);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FriendRequest");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(friendRequest);
        entityManager.getTransaction().commit();
    }
}
