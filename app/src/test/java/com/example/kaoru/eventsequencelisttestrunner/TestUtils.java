package com.example.kaoru.eventsequencelisttestrunner;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kaoru on 15/06/11.
 */
public class TestUtils {
    public static List<List<List<Enum<?>>>> seq(Enum<?>[][]... listOfList) {
        List<List<List<Enum<?>>>> data = new ArrayList<>();
        for (Enum<?>[][] list : listOfList) {
            ArrayList<List<Enum<?>>> newList = new ArrayList<>();
            for (Enum<?>[] row : list) {
                newList.add(Arrays.asList(row));
            }
            data.add(newList);
        }
        return seq(data);
    }

    public static List<List<List<Enum<?>>>> seq(List<List<List<Enum<?>>>> list) {
        if (list.size() < 2) {
            return list;
        }

        ArrayList<List<Enum<?>>> sequentialResult = new ArrayList<>();
        for (List<Enum<?>> list0 : list.get(0)) {
            for (List<Enum<?>> list1 : list.get(1)) {
                ArrayList<Enum<?>> line = new ArrayList<>(list0);
                line.addAll(list1);
                sequentialResult.add(line);
            }
        }

        ArrayList<List<List<Enum<?>>>> result = new ArrayList<>();

        ArrayList<List<List<Enum<?>>>> tmpList = new ArrayList<>();
        tmpList.addAll(list);
        tmpList.remove(0);
        tmpList.remove(0);
        tmpList.add(0, sequentialResult);
        result.addAll(seq(tmpList));
        return result;
    }

    public static List<List<List<Enum<?>>>> para(Enum<?>[][]... listOfList) {
        List<List<List<Enum<?>>>> data = new ArrayList<>();
        for (Enum<?>[][] list : listOfList) {
            ArrayList<List<Enum<?>>> newList = new ArrayList<>();
            for (Enum<?>[] row : list) {
                newList.add(Arrays.asList(row));
            }
            data.add(newList);
        }
        return para(data);
    }

    public static List<List<List<Enum<?>>>> para(List<List<List<Enum<?>>>> list) {
        if (list.size() < 2) {
            return list;
        }

        ArrayList<List<List<Enum<?>>>> result = new ArrayList<>();

        Integer[] indexList = new Integer[list.get(0).size() + list.get(1).size()];
        for (int i = 0; i < indexList.length; i++) indexList[i] = i;

        ICombinatoricsVector<Integer> indexVector = Factory.createVector(indexList);

        Generator<Integer> gen = Factory.createMultiCombinationGenerator(indexVector, list.get(0).size());
        for (ICombinatoricsVector<Integer> resultVector : gen) {
            ArrayList<List<Enum<?>>> combinationResult = new ArrayList<>(list.get(0).size() + list.get(1).size());
            Iterator<List<Enum<?>>> iterator0 = list.get(0).iterator();
            Iterator<List<Enum<?>>> iterator1 = list.get(1).iterator();
            for (int i = 0; i < list.get(0).size() + list.get(1).size(); i++) {
                combinationResult.add(resultVector.contains(i) ? iterator0.next() : iterator1.next());
            }

            ArrayList<List<List<Enum<?>>>> tmpList = new ArrayList<>();
            tmpList.addAll(list);
            tmpList.remove(0);
            tmpList.remove(0);
            tmpList.add(0, combinationResult);
            result.addAll(para(tmpList));
        }

        return result;
    }
}
