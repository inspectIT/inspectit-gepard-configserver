import { UUID } from "crypto";

export interface Agent {
  id: UUID;
  pid: number;
  serviceName: string;
  otelVersion: string;
  gepardVersion: string;
  startTime: string;
  javaVersion: string;
}
