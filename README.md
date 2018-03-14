# Request scoped component dependencies

An example project that presents request scoped component dependencies. The endpoints test the existence
of a HTTP status code by querying https://httpstatuses.com

The project compares passing the value of the X-Flow-Id header to the web service dependencies in two 
different ways:
- explicitly as an extra method parameter to the business logic (service.clj) and the web service 
client (client.clj)
- having a separate stuartsierra/component dependency for the flow-id in the web service client 
(client-2.clj). In this latter scenario the business logic (service-2.clj) does not have to know about 
the flow-id.

Project based on Friboo library.

## Development

Run  the application:

```
$ lein repl
user=> (reset)
```

For REPL-driven interactive development configuration variables can be provided in `dev-config.edn` file, 
which will be read on each system restart.

## Testing

```
$ lein test
```

## Building

```
$ lein uberjar
```

## Running

```
$ lein run
```

The following configuration environment variables are available:

| Variable | Meaning | Default | Example |
|---|---|---|---|
| API_EXAMPLE_PARAM | Example parameter with `:api-` prefix | `bar` | `foo` |

## Example requests

Calling the endpoint that passes flow-id as a parameter to the business logic and finally to the web 
service client method.

```
http get http://localhost:8080/exists/201 X-Flow-Id:asdf123456
```

Calling the endpoint that has flow-id as a request scoped dependency
```
http get http://localhost:8080/exists-with-fid-component/201 X-Flow-Id:asdf123456
```


## License

Copyright © 2018 Oskari Porkka

Distributed under the MIT License.
