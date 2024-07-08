import { UUID } from "crypto";

export interface Connection {
  id: UUID;
  pid: number;
  serviceName: string;
  otelVersion: string;
  gepardVersion: string;
  startTime: string;
  javaVersion: string;
}
