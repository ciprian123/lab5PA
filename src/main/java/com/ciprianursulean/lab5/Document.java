package com.ciprianursulean.lab5;

import javax.print.Doc;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document() {}

    public Document(String id, String name, String location) throws InvalidPathException {
        this.id = id;
        this.name = name;
        this.setLocation(location);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws InvalidPathException {
        if (location.startsWith("http://") || location.startsWith("https://")) {
            this.location = location;
        }
        else {
            File file = new File(location);
            if (!file.exists()) {
                throw new InvalidPathException();
            }
        }
        this.location = location;
    }

    public void addTag(String string, Object object) {
        tags.put(string, object);
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void printTags() {
        for (var tag : tags.keySet()) {
            System.out.println("{ "+ tag + " " + tags.get(tag) + " }");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Document");
        stringBuilder.append("\n");
        stringBuilder.append("Name: ");
        stringBuilder.append(name);
        stringBuilder.append("\n");
        stringBuilder.append("Id: ");
        stringBuilder.append(id);
        stringBuilder.append("\n");
        stringBuilder.append(location);
        stringBuilder.append("\n");
        stringBuilder.append("Tags: ");
        for (var tag : tags.keySet()) {
            stringBuilder.append(tag);
            stringBuilder.append(":");
            stringBuilder.append(tags.get(tag));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document)) {
            return false;
        }
        Document doc = (Document)obj;
        return this.getId().equals(doc.getId()) && this.getName().equals(doc.getName()) && this.getLocation().equals(doc.getLocation());
    }
}
