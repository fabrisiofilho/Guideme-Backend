package br.com.fabrisio.guideme.configuration;

import br.com.fabrisio.guideme.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuidemeContext {

    private static final ThreadLocal<UserEntity> currentUser = ThreadLocal.withInitial(() -> null);

    public static void setCurrentUser(UserEntity user) {
        currentUser.set(user);
    }

    public static UserEntity getCurrentUser() {
        return currentUser.get();
    }

    public static void clearUser() {
        currentUser.remove();
    }

}
