package com.example.houzw.clearmemobyandroidstudio;

import java.util.ArrayList;
import java.util.List;

import com.example.houzw.clearmemobyandroidstudio.domain.po.User;

import android.app.Activity;
import android.app.Application;

public class BaseActivity extends Application {
	public static List<Activity> activitys = new ArrayList<Activity>();
	public static User logingUser;
	public static Activity nowActivity;
}
