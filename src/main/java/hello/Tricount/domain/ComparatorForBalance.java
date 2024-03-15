package hello.Tricount.domain;

import java.util.Comparator;
public class ComparatorForBalance implements Comparator<ObjectForBalance> {



    @Override
    public int compare(ObjectForBalance o1, ObjectForBalance o2) {
        if(Math.abs(o1.getCost())>Math.abs(o2.getCost())){
            return -1;
        } else if (Math.abs(o1.getCost())==Math.abs(o2.getCost())) {

            return o1.getUser_name().compareTo(o2.getUser_name());

        } else{
           return 1;
        }
    }
}
