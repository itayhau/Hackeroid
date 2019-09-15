
        mDatabase.getReference().child("users/1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Toast.makeText(getBaseContext(), "User: " + user.getName() + " " + String.valueOf(user.getAge()),
                Toast.LENGTH_SHORT).show();
                int x = 1;
                // ...
                //writeNewMessage(...);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                int x= 1;
                Toast.makeText(getBaseContext(), "User: " + error,
                        Toast.LENGTH_SHORT).show();
            }
        });

