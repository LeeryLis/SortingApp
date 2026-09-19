package com.sortingapp.model;

public class StudentBuilder {

    //Это билдер класса Student.
    //Он создаёт объект Student, применяя проверки к его полям.
    //Переменные, оканчивающиеся на Set, нужны, чтобы убедится, что все поля были заполнены.


    private String groupNumber;
    private double averageScore;
    private int recordBookNumber;

    private boolean groupNumberSet;
    private boolean averageScoreSet;
    private boolean recordBookNumberSet;


    public StudentBuilder groupNumber(String groupNumber) {

        if (groupNumber == null || groupNumber.isBlank()) {
            throw new IllegalArgumentException( "Group number cannot be empty" );
        }
        this.groupNumber = groupNumber;
        this.groupNumberSet = true;
        return this;
    }

    public StudentBuilder averageScore(double averageScore) {
        if (averageScore < 0 || averageScore > 5) {
            throw new IllegalArgumentException( "Average score must be between 0 and 5" );
        }
        this.averageScore = averageScore;
        this.averageScoreSet = true;
        return this;
    }

    public StudentBuilder recordBookNumber(int recordBookNumber) {
        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException( "Record book number must be positive" );
        }
        this.recordBookNumber = recordBookNumber;
        this.recordBookNumberSet = true;
        return this;
    }

    public Student build() {
        if (!groupNumberSet || !averageScoreSet || !recordBookNumberSet) {
            throw new IllegalStateException( "All student parameters must be specified" );
        }
        return new Student( groupNumber, averageScore, recordBookNumber );
    }
}
