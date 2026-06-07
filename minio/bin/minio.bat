@echo off
chcp 65001
echo.
echo [信息] 运行MinIO文服务器。
echo.
title Minio文件服务


setx MINIO_ROOT_USER admin
setx MINIO_ROOT_PASSWORD admin123456
 
REM 获取当前脚本所在的目录
set "CURRENT_DIR=%~dp0"
REM 获取上级目录
set "PARENT_DIR=%~dp0.."

cd /d "%CURRENT_DIR%"
minio.exe server "%PARENT_DIR%\data" --console-address ":9001" --address ":9000" > "%PARENT_DIR%\logs\minio.log" 2>&1
pause
