package org.vso.presenters.implementations;

import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.User;
import org.vso.views.contracts.SearchView;
import org.vso.views.implementations.searchViews.SearchViewImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {
    private final UserDao<User> userDao;
    private final SearchView view;

    public SearchPresenter() {
        this.userDao = new UserDaoImpl();
        this.view = new SearchViewImpl();
    }

    public List<User> allUsersWithThisName(String name){
        List<User> usersWithThisName = new ArrayList<>();
        for (int i = 0; i < userDao.getAll().size(); i++) {
            if (userDao.getAll().get(i).getFirstName().equals(name)) {
                usersWithThisName.add(userDao.getAll().get(i));
            }
        }
        return usersWithThisName;
    }

    public User search(String firstName, String secondName) {
        User searchedUser = null;
        for (int i = 0; i < userDao.getAll().size(); i++) {
            if (firstName.equals(userDao.getAll().get(i).getFirstName()) &&
                    secondName.equals(userDao.getAll().get(i).getLastName())) {
                searchedUser = userDao.getAll().get(i);
            }
        }
        return searchedUser;
    }
}
