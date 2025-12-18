## Batch scripts overview
* **makedir.bat**: Creates the required folder structure used by the other scripts (based on its name; structure not shown in the snippet).​

* **makefiles.bat**: Walks through all subfolders, runs dir on each folder, and saves the listing into a text file inside that folder, then shows a full tree of files.​

* **typefiles.bat**: Prints the contents of all the generated * files across the a, a\b, a\c, and deeper subfolders in one combined output.​

* **movefiles.bat**: Moves all those * files from their subfolders into folder a, deletes the now-unused subfolders, renames each file to a month-day style name (January31.day, February28.day, etc.), and then shows the resulting tree.​

* **wildcards.bat**: Demonstrates use of wildcards in batch commands. ​

* **smalltree.bat**: Shows a small directory tree, probably as a simplified tree view of the working structure.​

## PowerShell script overview
* **pcinfo.ps1**: Collects hardware and OS information (system model, manufacturer, computer name, OS, CPU, RAM size, and C: drive capacity) using CIM/WMI, writes a human-readable “PC Hardware Information” report to pcinfo.txt, and instructs the user in the console to open that file
