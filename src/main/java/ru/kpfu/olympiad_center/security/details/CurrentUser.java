package ru.kpfu.olympiad_center.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.olympiad_center.models.User;
import ru.kpfu.olympiad_center.models.enums.UserState;

import java.util.Collection;
import java.util.Collections;

import static ru.kpfu.olympiad_center.models.enums.UserState.BANNED;
import static ru.kpfu.olympiad_center.models.enums.UserState.DELETED;

/**
 * 01.05.2018
 *
 * @author Robert Bagramov.
 */
public class CurrentUser implements UserDetails {
    private User user;

    public CurrentUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        UserState state = user.getState();
        return state != BANNED && state != DELETED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
