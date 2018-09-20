package ben.com.linklauncher.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ben.com.linklauncher.data.model.LinkModel;

public class SortLinkFactory {

    private static List<Comparator> criteriaList;

    static {
        criteriaList = new ArrayList<>();
        criteriaList.add(new SortByDate());
        criteriaList.add(new SortByStatus());
    }

    private static class SortByDate implements Comparator<LinkModel> {

        @Override
        public int compare(LinkModel t, LinkModel t1) {
            return t1.getDate().compareTo(t.getDate());
        }
    }

    private static class SortByStatus implements Comparator<LinkModel> {

        @Override
        public int compare(LinkModel t, LinkModel t1) {
            return t1.getStatus() - t.getStatus();
        }
    }

    public static Comparator getSort(int criteria) {
        return criteriaList.get(criteria);
    }

    public static int getCriteriaCount() {
        return criteriaList.size();
    }
}
