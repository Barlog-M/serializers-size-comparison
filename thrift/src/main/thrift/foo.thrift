namespace java com.example.model.thrift

typedef i64 POSIXTime

const byte PROTOCOL_VERSION_MAJOR = 0
const byte PROTOCOL_VERSION_MINOR = 1

struct Version {
	1: byte major = PROTOCOL_VERSION_MAJOR
	2: byte minor = PROTOCOL_VERSION_MINOR
}

enum MessageType {
	DATA
}

struct Message {
	1: MessageType type
	2: string header
	3: string value
}

struct Data {
	1: POSIXTime timestamp
	2: Version version
	3: list<Message> messages
}
