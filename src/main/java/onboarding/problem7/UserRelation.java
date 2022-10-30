package onboarding.problem7;

import java.util.*;
import java.util.stream.Collectors;

public class UserRelation {
    private static Map<String, Set<String>> userRelation = new HashMap<>();

    public static void addBulk(List<List<String>> relations) throws Exception {
        if(relations.size()<1 || relations.size()>10000) {
            throw new Exception("1개 이상 10_000개 이하의 리스트로만 초기화할 수 있습니다");
        }
        for (List<String> relation : relations) {
            add(relation);
        }
//        relations.stream().forEach(relation -> add(relation));
// TODO exception이 있으면 상대적으로 함수형으로 짜는게 지저분해진다. 예외처리가 필요해서. 다른 방법이 있을까?
    }

    public static void add(List<String> relation) throws Exception {
        if (relation.size() != 2) {
            throw new Exception("관계는 두명의 사용자만 맺을 수 있습니다");
        }
        if (!userRelation.containsKey(relation.get(0))) {
            userRelation.put(relation.get(0), new HashSet<>());
        }
        userRelation.get(relation.get(0)).add(relation.get(1));

        if (!userRelation.containsKey(relation.get(1))) {
            userRelation.put(relation.get(1), new HashSet<>());
        }
        userRelation.get(relation.get(1)).add(relation.get(0));

    }

    public static List<String> getFriends(String username) {
        return userRelation.get(username).stream().collect(Collectors.toList());
    }

    public static boolean hasRelation(String targetName, String compareName) {
        return userRelation.get(targetName).contains(compareName);
    }

    public static void clear() {
        userRelation.clear();
    }
}
