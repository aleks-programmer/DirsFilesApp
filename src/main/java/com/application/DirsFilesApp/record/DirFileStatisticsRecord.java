package com.application.DirsFilesApp.record;

import java.io.Serializable;

public class DirFileStatisticsRecord implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;

    private String dirFileName;
    private Long dirFileSize;
    private Boolean isDir;

    public String getDirFileName() {
        return dirFileName;
    }

    public void setDirFileName(String dirFileName) {
        this.dirFileName = dirFileName;
    }

    public Long getDirFileSize() {
        return dirFileSize;
    }

    public void setDirFileSize(Long dirFileSize) {
        this.dirFileSize = dirFileSize;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    @Override
    public int compareTo(Object o) {
        DirFileStatisticsRecord anotherObject = (DirFileStatisticsRecord) o;

        String o1 = this.getDirFileName();
        String o2 = anotherObject.getDirFileName();

        int i1 = 0;
        int i2 = 0;

        boolean bothNumbers = false;

        StringBuilder number1 = new StringBuilder();
        StringBuilder number2 = new StringBuilder();

        while (i1 < o1.length() || i2 < o2.length()) {
            String c1 = i1 < o1.length() ? String.valueOf(o1.charAt(i1)) : "";
            String c2 = i2 < o2.length() ? String.valueOf(o2.charAt(i2)) : "";

            if (bothNumbers) {
                boolean num1 = true;
                boolean num2 = true;

                Long a1 = 0L;
                try {
                    a1 = Long.valueOf(String.valueOf(c1));
                    number1.append(a1);
                } catch (NumberFormatException e) {
                    num1 = false;
                }

                Long a2 = 0L;
                try {
                    a2 = Long.valueOf(String.valueOf(c2));
                    number2.append(a2);
                } catch (NumberFormatException e) {
                    num2 = false;
                }

                if ((!num1 && !num2)) {
                    Long n1 = Long.valueOf(number1.toString());
                    Long n2 = Long.valueOf(number2.toString());

                    if (n1 != 0 || n2 != 0) {
                        if (!n1.equals(n2)) {
                            return n1.compareTo(n2);
                        } else {
                            number1 = new StringBuilder();
                            number2 = new StringBuilder();
                            bothNumbers = false;
                        }
                    }
                } else {
                    if (num1) {
                        if (i1 == o1.length() - 1) {
                            Long n1 = Long.valueOf(number1.toString());
                            Long n2 = Long.valueOf(number2.toString());

                            if (n1.equals(n2)) {
                                return -1;
                            } else {
                                return n1.compareTo(n2);
                            }
                        }
                        i1++;
                    }
                    if (num2) {
                        if (i2 == o2.length() - 1) {
                            Long n1 = Long.valueOf(number1.toString());
                            Long n2 = Long.valueOf(number2.toString());

                            if (n1.equals(n2)) {
                                return 1;
                            } else {
                                return n1.compareTo(n2);
                            }
                        }
                        i2++;
                    }
                }
            } else {
                try {
                    Long a1 = Long.valueOf(c1);
                    Long a2 = Long.valueOf(c2);
                    number1.append(a1);
                    number2.append(a2);
                    bothNumbers = true;
                } catch (NumberFormatException e) {
                    if (c1.compareToIgnoreCase(c2) != 0) {
                        return c1.compareToIgnoreCase(c2);
                    }
                }
                i1++;
                i2++;
            }
        }

        return this.getDirFileName().compareToIgnoreCase(anotherObject.getDirFileName());
    }
}
