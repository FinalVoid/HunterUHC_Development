package net.aksyo;

import net.aksyo.game.episodes.IEpisode;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.RoleType;
import org.reflections.Reflections;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Debug {

    public static void main(String[] args) {

        //distribute(32);

        /*EpisodeExecutor executor = episode -> {
            try {
                Method name = episode.getMethod("getName", null);
                System.out.println(name.invoke(episode.newInstance(), null));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        };

        executor.execute(HunterExamEpisode.class);*/

        Reflections reflections = new Reflections("net.aksyo.game.episodes");

        Set<Class<? extends IEpisode>> allClasses =
                reflections.getSubTypesOf(IEpisode.class);

        for(Class<? extends IEpisode> clazz : allClasses) {
            System.out.println(clazz.getName());
        }

    }

    public static void distribute(int players) {

        List<RoleType> availableRoles = new ArrayList<>();
        final long solos = Arrays.stream(RoleType.values()).filter(r -> r.get().debugSolo()).count();

        System.out.println(solos);

        for (RoleType r : RoleType.values()) {

            Role role = r.get();

            if (role.debugActivated()) {
                if (role.debugSolo()) {
                    availableRoles.add(r);
                    System.out.println(role.getClass().getSimpleName() + " is solo");

                } else {

                    for (int i = 0; i != Math.floor((players - solos) * (role.debugPercentage() * 0.01)); i++) {
                        availableRoles.add(r);
                        //if(availableRoles.size() == 20) return;
                        int actual = i + 1;
                        System.out.println(role.getClass().getSimpleName() + " has been added : " + actual);
                    }

                }

            }

        }

        System.out.println("Total roles : " + availableRoles.size());

        if (availableRoles.size() != players && availableRoles.size() > players) {

            throw new ArrayIndexOutOfBoundsException("Too much role for the amount of players");

        } else if (availableRoles.size() != players && availableRoles.size() < players) {

            int diff = players - availableRoles.size();
            List<RoleType> randomRole = Arrays.stream(RoleType.values()).filter(rl -> {
                Role factor = rl.get();
                return factor.debugActivated() && !factor.debugSolo();
            }).collect(Collectors.toList());

            for(int i = 0; i != diff; i++) {
                int random = new Random().nextInt(randomRole.size());
                availableRoles.add(randomRole.get(random));
                System.out.println("Force added role to complete : " + randomRole.get(random).get().getClass().getSimpleName());
            }

        }

        System.out.println("Total roles : " + availableRoles.size() + " after force");

        AtomicInteger phantom = new AtomicInteger();
        AtomicInteger royal = new AtomicInteger();
        AtomicInteger ant = new AtomicInteger();
        AtomicInteger hunter = new AtomicInteger();

        availableRoles.forEach(r -> {
            switch (r) {

                case HUNTER:
                    hunter.getAndIncrement();
                    break;
                case PHANTOM:
                    phantom.getAndIncrement();
                    break;
                case ROYALGUARD:
                    royal.getAndIncrement();
                    break;
                case ANT:
                    ant.getAndIncrement();
                    break;
            }
        });

        System.out.println("Number of phantom : " + phantom.get() + " percentage : " + (phantom.get() / players) * 100);
        System.out.println("Number of royalguard : " + royal.get() + " percentage : " + (royal.get() / players) * 100);
        System.out.println("Number of hunter : " + hunter.get() + " percentage : " + (hunter.get() / players) * 100);
        System.out.println("Number of ant : " + ant.get() + " percentage : " + (ant.get() / players) * 100);
    }
}
