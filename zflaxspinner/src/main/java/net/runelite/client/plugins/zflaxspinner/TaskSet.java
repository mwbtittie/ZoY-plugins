package net.runelite.client.plugins.zflaxspinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import net.runelite.api.Client;

public class TaskSet {
	public List<Task> taskList = new ArrayList<>();

	public TaskSet(Task... tasks) {
		taskList.addAll(Arrays.asList(tasks));
	}

	public void addAll(Task... tasks) {
		taskList.addAll(Arrays.asList(tasks));
	}

	public TaskSet(ZFlaxSpinnerPlugin plugin, Client client, ZFlaxSpinnerConfig config, ExecutorService executorService, Task... tasks) {
		taskList.addAll(Arrays.asList(tasks));
		verifyPlugin(plugin);
		verifyClient(client);
		verifyConfig(config);
		verifyExecutorService(executorService);
	}

	public void addAll(ZFlaxSpinnerPlugin plugin, Client client, ZFlaxSpinnerConfig config, MiscUtils utils, ExecutorService executorService, Task... tasks) {
		taskList.addAll(Arrays.asList(tasks));
		verifyPlugin(plugin);
		verifyClient(client);
		verifyConfig(config);
		verifyBotUtils(utils);
		verifyExecutorService(executorService);
	}

	public void clear() {
		taskList.clear();
	}

	public void verifyPlugin(ZFlaxSpinnerPlugin plugin) {
		if (plugin == null)
			return;
		for (Task task : taskList) {
			if (task.plugin == null)
				task.plugin = plugin;
		}
	}

	public void verifyClient(Client client) {
		if (client == null)
			return;
		for (Task task : taskList) {
			if (task.client == null)
				task.client = client;
		}
	}

	public void verifyConfig(ZFlaxSpinnerConfig config) {
		if (config == null)
			return;
		for (Task task : taskList) {
			if (task.config == null)
				task.config = config;
		}
	}


	public void verifyBotUtils(MiscUtils utils) {
		if (utils == null)
			return;
		for (Task task : this.taskList) {
			if (task.utils == null)
				task.utils = utils;
		}
	}

	public void verifyExecutorService(ExecutorService executorService)
	{
		if (executorService == null)
		{
			return;
		}

		for (Task task : taskList)
		{
			if (task.executorService == null)
			{
				task.executorService = executorService;
			}
		}
	}

	public Task getValidTask() {
		for (Task task : taskList) {
			if (task.validate())
				return task;
		}
		return null;
	}
}
