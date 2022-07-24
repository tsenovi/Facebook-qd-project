package org.vso.presenters.implementations;

import org.vso.models.dao.contracts.BaseDao;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.User;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;

public class SearchPresenterImpl implements BasePresenter {
    private final UserDao<User> allUsers;
    private final View view;

    public SearchPresenterImpl() {
        this.allUsers = new UserDaoImpl();
        this.view = new View();
    }

    public User search(String name){
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

    @Override
    public void onViewShown() {
        view.show("Name:");
        String name = view.getUserTextInput();
        search(name);
    }
}
