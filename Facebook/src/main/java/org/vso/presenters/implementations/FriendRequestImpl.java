package org.vso.presenters.implementations;

import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.User;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;

public class FriendRequestImpl implements BasePresenter {
    private final View view;
    private final UserDao<User> allUsers;

    public FriendRequestImpl() {
        this.view = new View();
        this.allUsers = new UserDaoImpl();
    }

    @Override
    public void onViewShown() {
        view.show("Name:");
        String name = view.getUserTextInput();
        search(name);
    }

    public void search(String name){
        for (int i = 0; i < allUsers.getAll().size(); i++) {
            if (allUsers.getAll().get(i).getFirstName().equals(name)){
                view.showNumber(i);
                view.showTextWithoutNextLine(". " + allUsers.getAll().get(i).getFirstName() +
                        " " + allUsers.getAll().get(i).getLastName() + "\n");

            }
        }
    }
}
