@echo off
setlocal
pushd %cd%

for /d /r %%a in (*) do (
  set "folder=%%~fa"
  setlocal enabledelayedexpansion
  set "lastfolder=!folder:~-1!"
  dir "%%~fa" > "%%~fa\F25_F10_franciscotejido_folder_!lastfolder!.txt"
  endlocal
)

popd
tree /f