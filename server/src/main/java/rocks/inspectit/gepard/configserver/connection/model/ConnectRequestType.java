/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model;

/**
 * Represents the type of connection request. If a already connected agent tries to connect again,
 * the request type is RECONNECT. DiSCONNECT is not used yet.
 */
public enum ConnectRequestType {
  CONNECT,
  RECONNECT,
  DISCONNECT
}
