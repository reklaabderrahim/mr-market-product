print("Started Adding the Users.");
db = db.getSiblingDB("admin");
db.createUser({
    user: "user",
    pwd: "password",
    roles: [{ role: "readWrite", db: "admin" }],
});
print("End Adding the User Roles.");