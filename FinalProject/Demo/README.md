# TKXDPM.KSCQ.20211-22

Hoàng Minh Lương: source code, database, video demo, installation guideline
Lê Thanh Lâm: srs, astah
Pathana Peungnhothoung: slide
Lê Minh Dương: sdd

## Getting Started

Welcome to the EBR project. Here is a guideline to help you get started.

## Folder Structure

The workspace contains the following folders, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `assets`: the folder to maintain static resources
- `test`: the folder for testing purpose

## Dependency Management
### Working with IntelliJ
Import the root directory of this repository after cloning under `File` -> `Open`

### SQL
Import `ebr.sql` in `assets/database` into MySQL

### JavaFX
1. `File` -> `Project Structure` -> `Libraries` -> `+`
2. Include the ***jars*** under either the `lib/linux/javafx-sdk-15` directory for Linux distro or the `lib/win/javafx-sdk-15` directory for Windows in the project.
3. Include the library into `Modules`.

### Add VM arguments
Click on `Run` -> `Edit Configurations...` then add these VM arguments:
- For Windows:
> `--add-opens
java.base/java.net=EcoBikeRental
--add-opens
java.base/java.lang.reflect=EcoBikeRental`
