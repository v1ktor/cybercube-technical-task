# `POST /pet`

- Validate that pet can be created with valid data
- Validate that pet cannot be created without a name
    - NB! Possible bug. The API documentation states that name is required field and possible 405? (I think more
      suitable response code is 400) error should have be thrown. However, the API allows to create a pet without a
      name.
- Validate that pet cannot be created without photos
    - NB! Possible bug. The API documentation states that photoUrls is required field and possible 405? (I think more
      suitable response code is 400) error should have be thrown. However, the API allows to create a pet without
      photos.
- Validate that 405 error is thrown when trying to create a pet without a body

# `DELETE /pet`

- Validate that pet can be deleted by id
- Validate that 404 error is thrown when trying to delete a pet that does not exist

# `GET /pet/findByStatus`

- Validate that pet can be found by status

# `GET /pet/{petId}`

- Validate that pet can be found by id
- Validate that 404 error is thrown when trying to find a pet that does not exist

# `PUT /pet`

- Validate that existing pet can be updated with valid data
- Validate that 404 error is thrown when trying to update a pet that does not exist
    - NB! Possible bug. Even specifying a wrong id in the update body, the last created pet is updated
