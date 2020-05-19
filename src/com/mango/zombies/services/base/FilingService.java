package com.mango.zombies.services.base;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.util.Timer;

public interface FilingService {

    //region Getters/Setters
    /**
     * Gets the auto save timer.
     */
    Timer getAutoSaveTimer();

    /**
     * Gets the debug folder.
     */
    File getDebugFolder();

    /**
     * Gets the enemies folder.
     */
    File getEnemiesFolder();

    /**
     * Gets the import folder.
     */
    File getImportFolder();

    /**
     * Gets the maps folder.
     */
    File getMapsFolder();

    /**
     * Gets the perks folder.
     */
    File getPerksFolder();

    /**
     * Gets the root folder.
     */
    File getRootFolder();

    /**
     * Gets the weapons folder.
     */
    File getWeaponsFolder();
    //endregion

    //region Public Methods
    /**
     * Deletes a file from a directory.
     * @param directory The directory where the file is located.
     * @param name The name of the file to delete, not including extension.
     */
    boolean deleteFile(File directory, String name);

    /**
     * Imports all available plugin data.
     */
    void importEverything();

    /**
     * Reads the contents of a file as deserializes it to an object.
     * @param filePath The path to the file to read.
     * @param deserializer The deserlializer for the object.
     * @param entity The class of the object.
     */
    <TReq> TReq readContents(String filePath, JsonDeserializer<TReq> deserializer, Class<TReq> entity);

    /**
     * Saves all plugin data.
     */
    void saveEverything();

    /**
     * Sets up the folders using the root directory.
     * @param rootFolder The plugin's root directory.
     */
    void setupFolders(File rootFolder);

    /**
     * Writes a file to a directory in JSON format.
     * @param directory The directory for where the file should be created.
     * @param name The name of the file, not including extension.
     * @param contents The contents of the file to be written.
     * @param serializer The serializer for the contents object.
     */
    <TReq> boolean writeFile(File directory, String name, TReq contents, JsonSerializer<TReq> serializer);

    /**
     * Writes a stack trace file.
     * @param error The error message.
     * @param stackTrace The error stack trace.
     */
    String writeStackTraceFile(String error, String stackTrace);
    //endregion
}
