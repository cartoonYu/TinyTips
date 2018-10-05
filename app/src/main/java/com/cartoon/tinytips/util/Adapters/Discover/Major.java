package com.cartoon.tinytips.util.Adapters;

public class Major{
        private int image1Id;

        private int image2Id;

        public Major(int image1Id,int image2Id) {
            this.image1Id = image1Id;
            this.image2Id = image2Id;
        }

        public int getImage1Id() {
            return image1Id;
        }

        public int getImage2Id(){
            return image2Id;
        }
}
