# API Practice: JSONPlaceholder

This folder is a lightweight, tool-independent API practice guide. It does not add a Java HTTP client or dependencies, so the Selenium, TestNG, and Cucumber framework stays isolated.

## Service and safety

Base URL: `https://jsonplaceholder.typicode.com`

[JSONPlaceholder](https://jsonplaceholder.typicode.com/) is a public fake REST API for testing and prototyping. Its `POST`, `PUT`, and `DELETE` responses are simulated: no real data is created, updated, or deleted.

## API and web-service basics

An API is an interface through which a client requests data or actions from a service. A web service exposes that interface over a network, commonly HTTP. A client sends a request to an endpoint; the backend applies business logic and returns an HTTP response with a status code, headers, and often JSON.

REST commonly models resources as URLs, uses HTTP methods, and exchanges JSON. SOAP is a protocol with formal XML envelopes and contract-based messaging (often WSDL). This practice section uses REST. CRUD maps to HTTP as Create = POST, Read = GET, Update = PUT, and Delete = DELETE.

## Requests

| Example | Method and URL | Parameters | Headers / body | Expected result |
| --- | --- | --- | --- | --- |
| List posts | `GET /posts` | None | `Accept: application/json`; no body | `200 OK`, JSON array, inspect `Content-Type` |
| Get one post | `GET /posts/{postId}` | Path: `postId=1` | `Accept: application/json`; no body | `200 OK`, object with `id`, `title`, and `body`; inspect `Content-Type` |
| Filter posts | `GET /posts?userId={userId}` | Query: `userId=1` | `Accept: application/json`; no body | `200 OK`, array of posts for user 1; inspect `Content-Type` |
| Create simulated post | `POST /posts` | None | `Content-Type` and `Accept`: `application/json`; JSON body below | `201 Created`, echoed object with generated `id`; inspect `Content-Type` |
| Replace simulated post | `PUT /posts/{postId}` | Path: `postId=1` | `Content-Type` and `Accept`: `application/json`; JSON body below | `200 OK`, replacement object; inspect `Content-Type` |
| Delete simulated post | `DELETE /posts/{postId}` | Path: `postId=1` | `Accept: application/json`; no body | `200 OK`, empty JSON object; inspect `Content-Type` |

POST body:

```json
{
  "title": "API practice post",
  "body": "Created as a simulated JSONPlaceholder request.",
  "userId": 1
}
```

PUT body:

```json
{
  "id": 1,
  "title": "Updated API practice post",
  "body": "Updated as a simulated JSONPlaceholder request.",
  "userId": 1
}
```

## Status-code reference

HTTP families are `1xx` informational, `2xx` success, `3xx` redirection, `4xx` client error, and `5xx` server error. The supplied collection also sends `GET /invalid-endpoint` and expects `404 Not Found`, demonstrating a negative `4xx` test.
