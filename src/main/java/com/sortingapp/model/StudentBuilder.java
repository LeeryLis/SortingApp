package com.sortingapp.model;

public class StudentBuilder {

    //Это билдер класса Student.
    //Переменные, оканчивающиеся на Set, нужны, чтобы убедится, что все поля были заполнены.

    private String groupNumber;
    private double averageScore;
    private int recordBookNumber;

    private boolean groupNumberSet;
    private boolean averageScoreSet;
    private boolean recordBookNumberSet;

    public Student build() {
        if (!groupNumberSet || !averageScoreSet || !recordBookNumberSet) {
            throw new IllegalStateException( "All student parameters must be specified" );
        }
        return new Student( groupNumber, averageScore, recordBookNumber );
    }

    public StudentBuilder groupNumber(String groupNumber) {
        if (groupNumber == null || groupNumber.isBlank()) {
            throw new IllegalArgumentException( "Group number cannot be empty" );
        }
        this.groupNumber = groupNumber;
        this.groupNumberSet = true;
        return this;
    }

    public StudentBuilder averageScore(double averageScore) {
        this.averageScore = averageScore;
        this.averageScoreSet = true;
        return this;
    }

    public StudentBuilder recordBookNumber(int recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
        this.recordBookNumberSet = true;
        return this;
    }
}
