package gui;

public interface ButtonClickListener {
    void onButtonClick();   // Existing method for Enter button
    void onStopClick();     // New method for Stop button
    void onResumeClick();   // New method for Resume button
}
