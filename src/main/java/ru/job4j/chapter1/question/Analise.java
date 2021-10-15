package ru.job4j.chapter1.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analise {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> map = new HashMap<>();
        previous.forEach(u -> map.put(u.getId(), u));
        int added = 0;
        int changed = 0;
        User userFromMap;
        for (User user : current) {
            userFromMap = map.remove(user.getId());
            if (userFromMap == null) {
                ++added;
            }
            if (userFromMap != null && !userFromMap.getName().equals(user.getName())) {
                ++changed;
            }
        }
        return new Info(added, changed, map.size());
    }
}
