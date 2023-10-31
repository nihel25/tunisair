package tn.esprit.tunisair.controller;

public class SentimentAnalysisResult {



        private int positiveCount;
        private int negativeCount;
        private int neutralCount;

        public SentimentAnalysisResult(int positiveCount, int negativeCount, int neutralCount) {
            this.positiveCount = positiveCount;
            this.negativeCount = negativeCount;
            this.neutralCount = neutralCount;
        }

        public int getPositiveCount() {
            return positiveCount;
        }

        public int getNegativeCount() {
            return negativeCount;
        }

        public int getNeutralCount() {
            return neutralCount;
        }
    }

    // Getters et setters


