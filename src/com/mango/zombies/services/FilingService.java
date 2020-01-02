package com.mango.zombies.services;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.*;

public class FilingService {

	//region Public Static Methods
	/**
	 * Deletes a file from a directory.
	 * @param directory The directory where the file is located.
	 * @param name The name of the file to delete, not including extension.
	 */
	public static void deleteFile(File directory, String name) {
		new File(directory + "/" + name + ".json").delete();
	}

	/**
	 * Reads the contents of a file as deserializes it to an object.
	 * @param filePath The path to the file to read.
	 * @param deserializer The deserlializer for the object.
	 * @param entity The class of the object.
	 */
	public static <TReq> TReq readContents(String filePath, JsonDeserializer<TReq> deserializer, Class<TReq> entity) {

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			StringBuilder contentBuilder = new StringBuilder();
			String currentLine;

			while ((currentLine = reader.readLine()) != null)
				contentBuilder.append(currentLine).append("\n");

			return new GsonBuilder().registerTypeAdapter(entity, deserializer).create().fromJson(contentBuilder.toString(), entity);

		} catch (Exception e) {
			System.out.println("Failed to open file: " + filePath);
		}

		return null;
	}

	/**
	 * Writes a file to a directory in JSON format.
	 * @param directory The directory for where the file should be created.
	 * @param name The name of the file, not including extension.
	 * @param contents The contents of the file to be written.
	 * @param serializer The serializer for the contents object.
	 */
	public static <TReq> void writeFile(File directory, String name, TReq contents, JsonSerializer<TReq> serializer) {

		String fileName = name + ".json";

		try (FileWriter writer = new FileWriter(directory + "/" + fileName)) {

			new GsonBuilder().registerTypeAdapter(contents.getClass(), serializer).setPrettyPrinting().create().toJson(contents, writer);

		} catch (Exception e) {
			System.out.println("Failed to write file: " + fileName);
		}
	}
	//endregion
}