package com.ely.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lior on 4/22/18.
 */

public class RecepieObject implements Parcelable {
    public String name;
    public List<Ingredients> ingredients;
    public List<Steps> steps;
    public String id;
    public String servings;
    public String image;

    public RecepieObject(String name, List<Ingredients> ingredients, List<Steps> steps, String id, String servings, String image) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.id = id;
        this.servings = servings;
        this.image = image;
    }



    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }





    protected RecepieObject(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<Ingredients>();
            in.readList(ingredients, Ingredients.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (in.readByte() == 0x01) {
            steps = new ArrayList<Steps>();
            in.readList(steps, Steps.class.getClassLoader());
        } else {
            steps = null;
        }
        id = in.readString();
        servings = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
        if (steps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(steps);
        }
        dest.writeString(id);
        dest.writeString(servings);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecepieObject> CREATOR = new Parcelable.Creator<RecepieObject>() {
        @Override
        public RecepieObject createFromParcel(Parcel in) {
            return new RecepieObject(in);
        }

        @Override
        public RecepieObject[] newArray(int size) {
            return new RecepieObject[size];
        }
    };
}