package gitautomation;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class GitRepoUpdater {

    public static void main(String[] args) throws Exception {
    	
    	boolean silent = false;

        for (String arg : args) {
            if (arg.equalsIgnoreCase("--silent")) {
                silent = true;
                break;
            }
        }
        String repoUrl, cloneDir, fileName, content;
        int choice;
    	if (silent) {
            // Use default values
    		long timestamp = System.currentTimeMillis();
    		String uniqueId = "" + timestamp;
    		repoUrl = "git@github.com:PonmaniShiva/cloudbees.git";
    		cloneDir = "temp"+uniqueId;
    		fileName = "test"+uniqueId+".txt";
    		content = "this is test text";
    		choice = 1;
            System.out.println("Running in silent mode with default values:");
            System.out.println("Repo Path: " + repoUrl);
            System.out.println("Clone directory: " + cloneDir);
            System.out.println("File name: " + fileName);
            System.out.println("Content: " + content);
            System.out.println("adding a new file");
        } else {
        Scanner scanner = new Scanner(System.in);

        // Get inputs
        System.out.print("Enter Git repo URL: ");
        repoUrl = scanner.nextLine();

        System.out.print("Enter local clone directory (e.g., tempRepo): ");
        cloneDir = scanner.nextLine();

        System.out.print("Enter file name (relative to repo root): ");
        fileName = scanner.nextLine();

        System.out.print("Enter content to write/append: ");
        content = scanner.nextLine();

        System.out.print("Do you want to (1) create new file or (2) update existing file? Enter 1 or 2: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        }

        // Clone repo
        runCommand("git clone " + repoUrl + " " + cloneDir);

        File file = new File(cloneDir + "/" + fileName);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // Write or append content
        if (choice == 1) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(content);
                System.out.println("File created with content.");
            }
        } else if (choice == 2) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine();
                writer.write(content);
                System.out.println("File updated with appended content.");
            }
        }

        // Commit and push changes
        runCommand("git add .", new File(cloneDir));
        runCommand("git commit -m \"Updated file " + fileName + "\"", new File(cloneDir));
        runCommand("git push", new File(cloneDir));
    }

    // Run command and print output
    private static void runCommand(String command) throws IOException, InterruptedException {
        runCommand(command, null);
    }

    private static void runCommand(String command, File dir) throws IOException, InterruptedException {
        System.out.println("Running: " + command);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("bash", "-c", command);
        if (dir != null) builder.directory(dir);
        builder.redirectErrorStream(true);
        Process process = builder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code " + exitCode + ": " + command);
        }
    }
}
