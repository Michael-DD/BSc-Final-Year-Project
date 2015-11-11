package geneticClasses;

/**
 * Created by Michal Dorko on 30/10/15.
 * BSc Final Year project
 * King's College London
 * Map-Reduce library for Genetic Algorithms
 * Licensed under the Academic Free License version 3.0
 */
public class FitnessCalculator {

    private static byte[] problemSolution;
    private static int lengthOfSolution;

    public static void setProblemSolution(byte[] problemSolution) {
        FitnessCalculator.problemSolution = problemSolution;
        FitnessCalculator.lengthOfSolution = problemSolution.length;
    }

    public static void setProblemSolution(String problemSolution) {
        FitnessCalculator.problemSolution = new byte[problemSolution.length()];
        int i = 0;
        for (char c : problemSolution.toCharArray()) {
           if (c == '0') {
               FitnessCalculator.problemSolution[i] = (byte) 0;
           } else {
               FitnessCalculator.problemSolution[i] = (byte) 1;
           }
           i++;
        }
    }

    public static int getLengthOfSolution() {
        return lengthOfSolution;
    }

    public static void calculateFitnessOfPopulation(Population p) {
        for(int i = 0; i < p.getSizeOfPopulation(); i++) {
            BinaryIndividualMapReduce binaryIndividualMapReduce = p.getIndividual(i);
            binaryIndividualMapReduce.setFitness(compareChromosomeAndSolution(binaryIndividualMapReduce.getChromosome()));
        }
    }

    protected static Integer compareChromosomeAndSolution(byte[] individualChromosome) {
        int fitness = 0;
        for(int i = 0; i < individualChromosome.length; i++) {
            if (individualChromosome[i] == problemSolution[i]) {
                fitness ++;
            }
        }
        return fitness;
    }


}