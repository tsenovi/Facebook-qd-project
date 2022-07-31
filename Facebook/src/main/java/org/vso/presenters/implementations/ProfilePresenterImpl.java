package org.vso.presenters.implementations;

import org.vso.models.data.Friendship;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.contracts.ProfileView;
import org.vso.views.implementations.friendshipViews.RespondingView;
import org.vso.views.implementations.friendshipViews.SendAFriendRequestView;
import org.vso.views.implementations.searchViews.SearchPage;
import org.vso.views.implementations.UploadImageViewImpl;
import org.vso.views.implementations.authenticationViews.LoginViewImpl;

public class ProfilePresenterImpl implements BasePresenter {

    private final ProfileView profileView;
    private final AuthenticationService authenticationService;
    private final FriendshipPresenter friendshipPresenter;

    public ProfilePresenterImpl(ProfileView profileView) {
        this.profileView = profileView;
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.friendshipPresenter = new FriendshipPresenter();
    }

    @Override
    public void onViewShown() {
//        while (authenticationService.hasLoggedUser()) {
            profileView.showProfileOptions();
            int userOption = profileView.getUserDecimalInput();
            switch (userOption) {
                case 1 : runLogoutProcess();break;
                case 2 : navigateToUploadPhotoView();break;
                case 3 : navigateToSearchPage();break;
                case 4 : navigateToSentAFriendRequest();break;
                case 5 : navigateToRespondingView();break;
                default : profileView.showOptionError();break;
            }
//        }
    }
    public void navigateToRespondingView(){
//        friendshipPresenter.showLoggedUserFriendRequest();
//        Friendship friendship = friendshipPresenter.getFriendship();
        new RespondingView();
    }

    public void navigateToSentAFriendRequest(){new SendAFriendRequestView();}

    public void navigateToSearchPage(){
        new SearchPage();
    }

    public void navigateToUploadPhotoView() {
        new UploadImageViewImpl();
    }

    public void runLogoutProcess() {
        profileView.onUserLogoutSelected();
        authenticationService.onUserLogoutSelected();
        navigateToLoginPage();
    }

    public void navigateToLoginPage() {
        LoginViewImpl.getInstance().showLoginPage();
    }
}
