package com.github.udonya.kaboom.config.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * YAML形式のコンフィグファイル
 *
 * @author udonya
 */
public abstract class AbstractYaml {
    /**
     * YAML ファイルの最大桁
     */
    private static final int MAX_LEN = 1024;

    /**
     * YAML ファイル名
     */
    private final String yamlRealFileName;

    /**
     * YAML ファイルパス
     */
    private final String yamlRealFilePath;

    /**
     * 書出し先 YAML ファイルインスタンス
     */
    private File yamlFile;

    /**
     * YAML コンフィグインスタンス
     */
    private FileConfiguration yamlConfig;

    /**
     * コンストラクタ
     *
     * @param yamlRealFileName
     *            YAMLファイル名
     * @param yamlRealFilePath
     *            YAMLファイルパス
     */
    public AbstractYaml(String yamlRealFileName, String yamlRealFilePath) {
        this.yamlRealFileName = yamlRealFileName;
        this.yamlRealFilePath = yamlRealFilePath;
    }

    /**
     * 初期化
     *
     * @param plg
     */
    public void init(JavaPlugin plg) {
        makeDataDir(plg.getDataFolder(), plg.getLogger());
        yamlFile = new File(plg.getDataFolder(), yamlRealFileName);
        makeDefault(plg.getResource(yamlRealFilePath + "/" + yamlRealFileName));
        yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(yamlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * データディレクトリが存在しなければ新規生成する。
     *
     * @param dataDir
     *            JavaPlugin.getDataFolder() の戻り値
     * @param log
     *            JavaPlugin.getLogger() の戻り値
     */
    private void makeDataDir(File dataDir, Logger log) {
        // ディレクトリ存在チェック
        if (dataDir.isDirectory()) {
            return;
        }
        // 初期生成
        log.info("Plugin's data directory " + dataDir.getAbsolutePath()
                + "not exists.");
        log.info("Creating directory: " + dataDir.getAbsolutePath() + "...");
        if (!dataDir.mkdirs()) {
            log.severe("Could't create data directory: "
                    + dataDir.getAbsolutePath());
        }
        log.info("Data directory created: " + dataDir.getAbsolutePath());
    }

    /**
     * 設定ファイルが存在しなければ デフォルト (JAR 内 YAML) をファイル出力する
     *
     * @param configResourceInJar
     *            JAR 内 YAML. JavaPlugin.getResource() の戻り値
     */
    private void makeDefault(InputStream configResourceInJar) {
        // ファイル存在チェック
        if (this.yamlFile.exists()) {
            return;
        }
        try {
            OutputStream out = new FileOutputStream(this.yamlFile);
            byte[] sBuf = new byte[MAX_LEN];
            int sLen;
            while ((sLen = configResourceInJar.read(sBuf)) > 0) {
                out.write(sBuf, 0, sLen);
            }
            out.close();
            configResourceInJar.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 設定ファイルを load する
     */
    protected void load() {
        try {
            yamlConfig.load(this.yamlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 設定ファイルを save する
     */
    protected void save() {
        try {
            yamlConfig.save(this.yamlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return yamlRealFileName;
    }

    protected String getRealFilePath() {
        return yamlRealFilePath;
    }

    protected FileConfiguration config() {
        return yamlConfig;
    }
}
