namespace java com.example.model.thrift

typedef i64 POSIXTime

const byte PROTOCOL_VERSION_MAJOR = 0
const byte PROTOCOL_VERSION_MINOR = 1

struct Version {
	1: byte major = PROTOCOL_VERSION_MAJOR
	2: byte minor = PROTOCOL_VERSION_MINOR
}

enum Type {
	RECORD
}

struct Record {
	1: Type type
	2: i32 value
	3: string message
	4: string description
}

struct Data {
	1: POSIXTime timestamp
	2: Version version
	3: list<Record> records
}
