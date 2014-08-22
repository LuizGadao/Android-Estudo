package com.luizgadao.androidestudos.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by luizcarlos on 22/08/14.
 */
public class ListImgs implements Serializable {
    public static final String Key = "imgs";

    private ArrayList<BitmapSerializable> imgs;

    public ListImgs(ArrayList<BitmapSerializable> imgs)
    {
        this.imgs = imgs;
    }

    public ArrayList<BitmapSerializable> getImgs() {
        return imgs;
    }
}
