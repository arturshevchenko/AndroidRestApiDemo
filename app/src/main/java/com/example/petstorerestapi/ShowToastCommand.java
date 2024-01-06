package com.example.petstorerestapi;

import com.appspector.sdk.monitors.commands.BaseCommand;
import com.appspector.sdk.monitors.commands.annotations.Argument;
import com.appspector.sdk.monitors.commands.annotations.Command;

@Command(value="Show message", category = "Application")
public class ShowToastCommand extends BaseCommand<Integer> {

    @Argument(isRequired = false)
    public String message;
}
