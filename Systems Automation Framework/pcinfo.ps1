$System = Get-CimInstance CIM_ComputerSystem
$OS = Get-CimInstance CIM_OperatingSystem
$CPU = Get-CimInstance CIM_Processor
$Storage = Get-CimInstance Win32_LogicalDisk -Filter "DeviceID = 'C:'"
$InfoFile = "pcinfo.txt"
cd > pcinfo.txt

Write-Host "Open pcinfo.txt to view PC Hardware Information"
"PC Hardware Information" >> $InfoFile
"                       " >> $InfoFile
"This is " + $System.Model + ", manufactured by " + $System.Manufacturer + "." >> $InfoFile
"The name of this computer is " + $System.name + "." >> $InfoFile
"The operating system installed on this computer is " + $OS + "." >> $InfoFile
"                       " >> $InfoFile
"The CPU, RAM and free storage are as follows:" >> $InfoFile
"                       " >> $InfoFile
"CPU: " + $CPU >> $InfoFile
"RAM: " + "{0:N2}" -f ($System.TotalPhysicalMemory/1GB) + " GB" >> $InfoFile
"HDD Capacity: " + "{0:N2}" -f ($Storage.Size/1GB) + " GB" >> $InfoFile
 
 