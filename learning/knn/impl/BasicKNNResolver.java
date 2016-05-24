package learning.knn.impl;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import learning.knn.KNNResolver;
import learning.knn.dto.Movie;
import rules.positiongenerator.PositionGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created on 06.04.2016.
 *
 * @author Źmicier Dzikański
 */
public class BasicKNNResolver implements KNNResolver {
    LinkedList<Movie> movies = new LinkedList<Movie>();

    private class Pair {
        private float estimate;
        private int movie;

        private Pair(float estimate, int movie) {
            this.estimate = estimate;
            this.movie = movie;
        }
    }

    public void load(String fileName) {
        if(movies.size() > 0) return;

        try {
            Scanner sc = new Scanner(new File(fileName));
            while(sc.hasNext())  {
               movies.add(new Movie(sc.nextLine()));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FirstPlayerMovie findNearest(Position position, List<FirstPlayerMovie> moviesList) {
        ArrayList<Pair> pairs = new ArrayList<Pair>(movies.size());
        int[] pos = toIntArray(position);

        for(Movie movie: movies) {
            if(moviesList.contains(FirstPlayerMovie.getMovie(movie.getMovie()))) {
                float currDiff = calculateLength(pos, movie);
                pairs.add(new Pair(currDiff, movie.getMovie()));
            }
        }
        Collections.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.estimate > o2.estimate) {
                    return -1;
                } else if(o1.estimate == o2.estimate) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return FirstPlayerMovie.getMovie(pairs.get(0).movie);
        /*
        int[] nns = new int[4];
        for(Pair pair: pairs) {
            if(++nns[pair.movie] > 9) return FirstPlayerMovie.getMovie(pair.movie);
        }

        int max = -1;
        int best = -1;
        for(int i= 0; i < nns.length; i++) {
            if(nns[i] > max) {
                max = nns[i];
                best = i;
            }
        }
        return best == -1 ? moviesList.get(0) : FirstPlayerMovie.getMovie(best);*/
    }

    private float calculateLength(int[] position, Movie movie) {
        float estimate = 0;
        int size = position.length;
        for(int i = 0; i < size; i++) {
            estimate += compareField1(position, movie, i);
        }
        for(int i = 0; i < size; i++) {
            for(int j = i+1; j<size; j++) {
                estimate += compareField2(position, movie, i, j);
            }
        }
        /*for(int i = 0; i < size; i++) {
            for(int j = i+1; j<size; j++) {
                for(int k = j+1; k<size; j++) {
                    estimate += compareField3(position, movie, i, j, k);
                }
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = i+1; j<size; j++) {
                for(int k = j+1; k<size; j++) {
                    for(int l = k+1; l < size; i++) {
                        estimate += compareField4(position, movie, i, j, k, l);
                    }
                }
            }
        }*/

        return estimate;
    }

    private int[] toIntArray(Position p) {
        int[] array = new int[p.getSize() * p.getSize()];
        for(int x = 0; x < p.getSize(); x++) {
            for(int y = 0; y < p.getSize(); y++) {
                if(p.getTile(x, y) != null) {
                    array[x * p.getSize() + y] = (Integer) p.getTile(x, y).getValue();
                } else {
                    array[x * p.getSize() + y] = 0;
                }
            }
        }
        return array;
    }

    private boolean bothZeroOrNot(int i, int j) {
        return !(i == 0 ^ j == 0);
    }

    private boolean bothTwoOrNot(int i, int j) {
        return !(i == 2 ^ j == 2);
    }

    private float compareField1(int[] position, Movie movie, int i) {
        if(!bothZeroOrNot(position[i], movie.getPosition()[i])) {
            return 0;
        } else if(!bothTwoOrNot(position[i], movie.getPosition()[i])) {
            return 0.5f;
        } else {
            return 1;
        }
    }

    private float calculateDiff(int x, int y) {
        if(x == y) return 1f;
        if(x == 2 * y) return 1.5f;
        if(x > y) return 2f;
        if(y == 2 * x) return 0.5f;
        return 0f;
    }

    private float compareField2(int[] position, Movie movie, int i, int j) {
        int diff1 = i/4 - j/4;
        int diff2 = i%4 - j%4;

        return 4 - Math.abs(calculateDiff(position[i], position[j]) - calculateDiff(movie.getPosition()[i], movie.getPosition()[j]))
                / (float)(diff1+diff2);
    }
}
