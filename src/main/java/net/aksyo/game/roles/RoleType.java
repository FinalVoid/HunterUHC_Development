package net.aksyo.game.roles;

import net.aksyo.game.roles.gameroles.*;

import java.util.function.Supplier;

public enum RoleType {

    GON(Gon::new),
    KILLUA(Killua::new),
    KURAPIKA(Kurapika::new),
    NETERO(Netero::new),
    HUNTER(Hunter::new),
    PHANTOM(PhantomMember::new),
    MERUEM(Meruem::new),
    ROYALGUARD(RoyalGuard::new),
    ANT(Ant::new);


    private final Supplier<Role> factory;

    RoleType(Supplier<Role> factory) {
        this.factory = factory;
    }

    public Role get() {
        return factory.get();
    }


}
